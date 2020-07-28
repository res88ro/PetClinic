package sda.project.PetClinic.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sda.project.PetClinic.model.Client;
import sda.project.PetClinic.repositories.ClientRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ClientController {
    @Autowired
    ClientRepository clientRepository;

    @GetMapping("/clients")
    public String getAllClients(Model model) {
        List<Client> clientList = clientRepository.findAll();
        model.addAttribute("lista", clientList);
        return "all-clients";
    }

    @GetMapping("/clients/delete/{id}")
    public String deleteClient(@PathVariable Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No client found with id: " + id));
        clientRepository.delete(client);
        return "redirect:/clients";
    }

    @GetMapping("/clients/update/{id}")
    public String goToEditPage(@PathVariable Long id, Model model) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No client found with id: " + id));
        model.addAttribute("client", client);
        return "client";
    }

    @GetMapping("/clients/client.html")
    public String goToEditPage(@ModelAttribute Client client) {
        return "client";
    }

    @PostMapping("/clients/save/{id}")
    public String saveClient(@Valid Client client, @PathVariable(required = false) Long id, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "all-clients";
        } else {
            clientRepository.save(client);
            return "redirect:/clients";
        }

    }

    @PostMapping("/clients/save")
    public String saveUser(@Validated Client client, BindingResult bindingResult, Model model) {
        return saveClient(client, null, bindingResult, model);
    }
}
