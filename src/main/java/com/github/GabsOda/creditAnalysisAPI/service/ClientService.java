package com.github.GabsOda.creditAnalysisAPI.service;

import com.github.GabsOda.creditAnalysisAPI.dto.request.ClientDTO;
import com.github.GabsOda.creditAnalysisAPI.dto.response.MessageResponseDTO;
import com.github.GabsOda.creditAnalysisAPI.entity.Client;
import com.github.GabsOda.creditAnalysisAPI.exception.ClientNotFoundException;
import com.github.GabsOda.creditAnalysisAPI.mapper.ClientMapper;
import com.github.GabsOda.creditAnalysisAPI.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ClientService {

    private ClientRepository clientRepository;

    private final ClientMapper clientMapper = ClientMapper.INSTANCE;

    public MessageResponseDTO createClient(ClientDTO clientDTO) {
        Client clientToSave = clientMapper.toModel(clientDTO);
        Client savedClient = clientRepository.save(clientToSave);

        return createMessageResponse(savedClient.getId(), "Created Client with ID: ");
    }

    public List<ClientDTO> listAll() {
        List<Client> allClient = clientRepository.findAll();

        return allClient.stream()
                .map(clientMapper::toDto)
                .collect(Collectors.toList());
    }

    public ClientDTO findById(Long id) throws ClientNotFoundException{
        Client client = verifyIfExists(id);

        return clientMapper.toDto(client);
    }

    public MessageResponseDTO updateById(Long id, ClientDTO clientDTO) throws ClientNotFoundException {
        verifyIfExists(id);

        Client clientToUpdate = clientMapper.toModel(clientDTO);
        Client updatedClient = clientRepository.save(clientToUpdate);

        return createMessageResponse(updatedClient.getId(), "Updated Client with ID: ");
    }

    public MessageResponseDTO deleteById(Long id) throws ClientNotFoundException {
        verifyIfExists(id);

        clientRepository.deleteById(id);

        return createMessageResponse(id, "Deleted client with ID: ");
    }

    private Client verifyIfExists(Long id) throws ClientNotFoundException{
        return clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));
    }

    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO.builder()
                .message(message + id)
                .build();
    }

}