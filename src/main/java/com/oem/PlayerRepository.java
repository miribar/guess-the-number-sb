package com.oem;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends CrudRepository <Player, Long> {
                                                        //<T: type, Serializable: id field of the type>

}
