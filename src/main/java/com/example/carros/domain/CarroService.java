package com.example.carros.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarroService {

    @Autowired
    private CarroRepository rep;

    public Iterable<Carro> getCarros() {
        return rep.findAll();
    }

    public Optional<Carro> getCarroById(Long id) {
        return rep.findById(id);
    }

    public Iterable<Carro> getCarrosByTipo(String tipo) {
        return rep.findByTipo(tipo);
    }

    public Carro insert(Carro carro) {
        return rep.save(carro);
    }

    public Carro update(Carro carro, Long id) {
        Assert.notNull(id, "Não foi possível atualizar o registro");

        // Busca o carro no banco de dados
        Optional<Carro> optional = getCarroById(id);
        if (optional.isPresent()) {
            Carro db = optional.get();
            // Copia as propriedades
            db.setNome(carro.getNome());
            db.setTipo(carro.getTipo());
            System.out.println("Carro id: " + db.getId());

            // Atualiza o carro
            rep.save(db);

            return db;
        } else {
            throw new RuntimeException("Não foi possível atualizar o registro");
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

    public void delete(Long id) {
        Optional<Carro> carro = getCarroById(id);
        if (carro.isPresent()) {
            rep.deleteById(id);
        }
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
