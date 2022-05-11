package com.cadastrocliente.controller;

import com.cadastrocliente.model.Client;
import com.cadastrocliente.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/client")
@Api(value = "API Client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    @ApiOperation(value = "Create a new client.")
    @ApiResponses(value = {
            @ApiResponse(code = 422, message = "Invalid format."),
    })
    public Client createClient(@RequestBody @Valid Client client) {
        return clientService.createClient(client);
    }

    @GetMapping(value = "/{cpf}")
    @ApiOperation(value = "Return a single client by cpf informed.")
    @ApiResponses(value = {
            @ApiResponse(code = 422, message = "Invalid CPF format."),
    })
    public Client getClientByCPF(@PathVariable("cpf") final String cpf) {
        return clientService.getClientByCpf(cpf);
    }

    @GetMapping
    @ApiOperation(value = "Return a paginated list of clients.")
    @ApiResponses(value = {
            @ApiResponse(code = 406, message = "Page or pageSize not acceptable."),
    })
    public List<Client> getAllClients(@RequestParam("page") final int page, @RequestParam("pageSize") final int pageSize) {
        return clientService.getAllClients(page, pageSize);
    }
}