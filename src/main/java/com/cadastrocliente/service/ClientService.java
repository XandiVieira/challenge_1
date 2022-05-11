package com.cadastrocliente.service;

import com.cadastrocliente.exception.CustomAlreadyExistsException;
import com.cadastrocliente.exception.CustomIllegalArgumentException;
import com.cadastrocliente.exception.CustomInvalidCpfException;
import com.cadastrocliente.exception.CustomInvalidDateOfBirthException;
import com.cadastrocliente.model.Client;
import com.cadastrocliente.repository.ClientRepository;
import com.cadastrocliente.utils.ValidateCPF;
import com.cadastrocliente.utils.ValidateDateOfBirth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client createClient(Client client) {
        log.info("Creating client...");
        client.setCpf(ValidateCPF.formatCpf(client.getCpf()));
        if (!ValidateCPF.isValid(client.getCpf())) {
            throw new CustomInvalidCpfException("Cpf invalid format. Expected formats are: 000.000.000-00 or 00000000000");
        }
        if (!ValidateDateOfBirth.isValid(client.getDateOfBirth())) {
            throw new CustomInvalidDateOfBirthException("Date of birth is invalid. Expected format is: dd/MM/yyyy");
        }
        if (!clientRepository.existsByCpf(client.getCpf())) {
            return clientRepository.save(client);
        } else {
            throw new CustomAlreadyExistsException("There is already a client with cpf: " + client.getCpf());
        }
    }

    public List<Client> getAllClients(int page, int pageSize) {
        log.info("Retrieving all clients...");
        log.info("Page: " + page + " - PageSize: " + pageSize);
        PageRequest pageable;
        if (page >= 0 && pageSize > 0) {
            pageable = PageRequest.of(page, pageSize, Sort.by("cpf"));
            List<Client> content = clientRepository.findAll(pageable).getContent();
            log.info(content.size() + " clients found.");
            return content;
        } else {
            throw new CustomIllegalArgumentException("Page must be greater than or equal to 0. PageSize must be greater than 0.");
        }
    }

    public Client getClientByCpf(String cpf) {
        log.info("Retrieving clint by cpf: " + cpf);
        cpf = ValidateCPF.formatCpf(cpf);
        if (ValidateCPF.isValid(cpf)) {
            return clientRepository.findByCpf(cpf);
        } else {
            throw new CustomInvalidCpfException("Cpf invalid format. Expected formats are: 000.000.000-00 or 00000000000");
        }
    }
}