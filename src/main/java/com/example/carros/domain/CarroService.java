package com.example.carros.domain;

import com.example.carros.domain.dto.CarroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarroService {

    @Autowired
    private CarroRepository rep;

    public List<CarroDTO> getCarros() {
        List<Carro> carros = rep.findAll();

        // Usando Lambda
                                       // c -> CarroDTO.create(c).collect(Collectors.toList());
        return rep.findAll().stream().map(CarroDTO::create).collect(Collectors.toList());
        // Usando for
//        List<CarroDTO> list = new ArrayList<>();
//        for (Carro c : carros) {
//            list.add(new CarroDTO(c));
//        }
//        return list;
    }

    public Optional<CarroDTO> getCarroById(Long id) {
        // Usando lambdas
        return rep.findById(id).map(CarroDTO::create);

        // Usando if
//        Optional<Carro> carro = rep.findById(id);
//        if (carro.isPresent()) {
//            return Optional.of(new CarroDTO(carro.get()));
//        } else {
//            return null;
//        }
    }

    public List<CarroDTO> getCarrosByTipo(String tipo) {
        return rep.findByTipo(tipo).stream().map(CarroDTO::create).collect(Collectors.toList());
    }

    public CarroDTO insert(Carro carro) {
        Assert.isNull(carro.getId(), "Não foi possível inserir o registro ");

        return CarroDTO.create(rep.save(carro));
    }

    public CarroDTO update(Carro carro, Long id) {
        Assert.notNull(id, "Não foi possível atualizar o registro");

        // Busca o carro no banco de dados
        Optional<Carro> optional = rep.findById(id);
        if (optional.isPresent()) {
            Carro db = optional.get();
            // Copia as propriedades
            db.setNome(carro.getNome());
            db.setTipo(carro.getTipo());
            System.out.println("Carro id: " + db.getId());

            // Atualiza o carro
            rep.save(db);

            return CarroDTO.create(db);
        } else {
            return null;
            //throw new RuntimeException("Não foi possível atualizar o registro");
        }

        /*getCarroById(id).map(db -> {
                    // Copiar as propriedades
                    db.setNome(carro.getNome());
                    db.setTipo(carro.getTipo());
                    System.out.println("Carro id: " + db.getId());
                    // Atualiza o carro
                    rep.save(db);

                    return db;
                }).orElseThrow(() -> RuntimeException("Não foi possível atualizar o registro"));*/

    }

    public boolean delete(Long id) {
        if (getCarroById(id).isPresent()) {
            rep.deleteById(id);
            return true;
        }
        return false;
    }

/*
    public List<Carro> getCarrosFakes() {
        List<Carro> carros = new ArrayList<>();

        carros.add(new Carro(1L, "Fusca", "Clássicos"));
        carros.add(new Carro(1L, "Brasilia", "Clássicos"));
        carros.add(new Carro(1L, "Chevette", "Clássicos"));

        return carros;
    }
*/

}
