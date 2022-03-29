package io.getarrays.server.services;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import io.getarrays.server.enumerations.Status;
import io.getarrays.server.model.Server;
import io.getarrays.server.repository.ServerRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service // para que la clase sea usada como servicio
@Transactional
public class ServerServicesImplementacion {

	@Autowired
	private ServerRepository serverRepository;

	public Server create(Server server) {
		System.out.println("Creando nuevo servidor" + server.getName());
		return this.serverRepository.save(server);
	}


	public Server ping(String ipAddress) throws IOException {
		System.out.println("Haciendo Ping a servidor " + ipAddress);
		Server server = serverRepository.findByIpAddress(ipAddress);
		InetAddress address = InetAddress.getByName(ipAddress);
		server.setStatus(address.isReachable(10000) ? Status.SERVER_UP : Status.SERVER_DOWN);
		this.serverRepository.save(server);

		return server;
	}

	public Collection<Server> list(int limit) {
		System.out.println("Mostrando todos los servidores..." + limit);
		return this.serverRepository.findAll(PageRequest.of(0, limit)).toList();
	}

	public Server get(Long id) {
		System.out.println("Mostrando servidor x ID: " + id);
		return this.serverRepository.findById(id).get();
	}

	public Server update(Server server) {
		System.out.println("Actualizando nuevo servidor" + server.getName());
		return this.serverRepository.save(server);
	}

	public Boolean delete(Long id) {
		System.out.println("Eliminando servidor con ID: " + id);
		this.serverRepository.deleteById(id);
		return true;
	}

}
