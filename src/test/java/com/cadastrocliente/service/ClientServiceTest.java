package com.cadastrocliente.service;

import com.cadastrocliente.exception.CustomAlreadyExistsException;
import com.cadastrocliente.exception.CustomIllegalArgumentException;
import com.cadastrocliente.exception.CustomInvalidCpfException;
import com.cadastrocliente.exception.CustomInvalidDateOfBirthException;
import com.cadastrocliente.model.Client;
import com.cadastrocliente.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepository clientRepository;

    @Test
    void shouldCreateClientSuccessfully() {
        Client client = new Client(1L, "Jose", "84272004042", "30/06/1997");

        when(clientRepository.save(client)).thenReturn(client);

        Client createdClient = clientService.createClient(client);

        verify(clientRepository, times(1)).save(client);
        assertEquals(client.getCpf(), createdClient.getCpf());
        assertEquals(client.getName(), createdClient.getName());
        assertEquals(client.getId(), createdClient.getId());
        assertEquals(client.getDateOfBirth(), createdClient.getDateOfBirth());
    }

    @Test
    void shouldThrowExceptionOnCreatingClientWithInvalidCpf() {
        Client client = new Client(1L, "Jose", "123456789", "30/06/1997");

        assertThatThrownBy(() -> clientService.createClient(client))
                .isInstanceOf(CustomInvalidCpfException.class)
                .hasMessageContaining("Cpf invalid format. Expected formats are: 000.000.000-00 or 00000000000");

        verify(clientRepository, times(0)).save(client);
    }

    @Test
    void shouldThrowExceptionOnGettingAllClientsWithInvalidPagination() {
        assertThatThrownBy(() -> clientService.getAllClients(0, 0))
                .isInstanceOf(CustomIllegalArgumentException.class)
                .hasMessageContaining("Page must be greater than or equal to 0. PageSize must be greater than 0.");
    }

    @Test
    void shouldThrowExceptionOnCreatingClientWithInvalidDateOfBirth() {
        Client client = new Client(1L, "Jose", "00896126048", "32/06/1997");

        assertThatThrownBy(() -> clientService.createClient(client))
                .isInstanceOf(CustomInvalidDateOfBirthException.class)
                .hasMessageContaining("Date of birth is invalid. Expected format is: dd/MM/yyyy");

        verify(clientRepository, times(0)).save(client);
    }

    @Test
    void shouldThrowExceptionOnCreatingClientWithExistingCpf() {
        Client client = new Client(1L, "Jose", "00896126048", "30/06/1997");

        when(clientRepository.existsByCpf(client.getCpf())).thenReturn(true);

        assertThatThrownBy(() -> clientService.createClient(client))
                .isInstanceOf(CustomAlreadyExistsException.class)
                .hasMessageContaining("There is already a client with cpf: " + client.getCpf());

        verify(clientRepository, times(0)).save(client);
    }

    @Test
    void shouldGetAllClientsSuccessfully() {
        Client client1 = new Client(1L, "Jose", "00896126048", "30/06/1997");
        Client client2 = new Client(2L, "Maria", "42918288020", "05/04/1199");
        Client client3 = new Client(3L, "Lucas", "84272004042", "15/11/2003");

        Page<Client> clients = new PageImpl<>(Arrays.asList(client1, client2, client3));
        PageRequest pageable = PageRequest.of(0, 3, Sort.by("cpf"));
        when(clientRepository.findAll(pageable)).thenReturn(clients);
        List<Client> returnedClients = clientService.getAllClients(0, 3);

        assertEquals(clients.getTotalElements(), returnedClients.size());
        verify(clientRepository, times(1)).findAll(pageable);
    }

    @Test
    void shouldGetClientByCpfSuccessfully() {
        Client client = new Client(1L, "Jose", "00896126048", "30/06/1997");

        when(clientRepository.findByCpf(client.getCpf())).thenReturn(client);

        Client clientByCpf = clientService.getClientByCpf(client.getCpf());

        assertEquals(client.getCpf(), clientByCpf.getCpf());
        assertEquals(client.getName(), clientByCpf.getName());
        assertEquals(client.getId(), clientByCpf.getId());
        assertEquals(client.getDateOfBirth(), clientByCpf.getDateOfBirth());
        verify(clientRepository, times(1)).findByCpf(client.getCpf());
    }

    @Test
    void shouldThrowExceptionOnGettingClientByInvalidCpf() {
        Client client = new Client(1L, "Jose", "00896126048", "30/06/1997");

        assertThatThrownBy(() -> clientService.getClientByCpf("123456789"))
                .isInstanceOf(CustomInvalidCpfException.class)
                .hasMessageContaining("Cpf invalid format. Expected formats are: 000.000.000-00 or 00000000000");

        verify(clientRepository, times(0)).findByCpf(client.getCpf());
    }
}