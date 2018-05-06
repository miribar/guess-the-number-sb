package com.oem;

import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository <Player, Long> {
}
