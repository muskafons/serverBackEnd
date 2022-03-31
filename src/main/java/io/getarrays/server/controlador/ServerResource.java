package io.getarrays.server.controlador;

import java.io.IOException;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.getarrays.server.model.Server;
import io.getarrays.server.services.ServerServicesImplementacion;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class ServerResource {

	@Autowired
	private ServerServicesImplementacion serverServicesImplementacion;

	@GetMapping("list")
	public Collection<Server> listaServers() {
		return serverServicesImplementacion.list(30);
	}

	@GetMapping("ping/{ipAddress}")
	public Server pingServer(@PathVariable("ipAddress") String ipAddress) throws IOException {
		return serverServicesImplementacion.ping(ipAddress);
	}

	@GetMapping("get/{id}")
	public Server getServerById(@PathVariable("id") Long id) {
		return serverServicesImplementacion.get(id);
	}


	@PostMapping("save")
	public Server saveServer(@RequestBody @Valid Server server) {
		return serverServicesImplementacion.create(server);
	}

	@PutMapping("actualizar")
	public Server updateServer(@RequestBody @Valid Server server) {
		return serverServicesImplementacion.update(server);
	}

	@DeleteMapping("delete/{id}")
	public Boolean deleteServerById(@PathVariable("id") Long id) {
		return serverServicesImplementacion.delete(id);
	}

}
