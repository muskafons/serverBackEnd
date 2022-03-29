package io.getarrays.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.getarrays.server.model.Server;


public interface ServerRepository extends JpaRepository<Server, Long> {

	Server findByIpAddress(String ipAddress);
	// Server findByName(String Name); //No funcionara xq solo devolvera un servidor
	// y si muchos nombres coinciden, se generará excepcion

}
