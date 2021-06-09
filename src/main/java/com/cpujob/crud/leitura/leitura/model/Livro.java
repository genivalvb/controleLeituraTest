package com.cpujob.crud.leitura.leitura.model;

import com.cpujob.crud.leitura.leitura.enums.LivroType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name="livro")
@NoArgsConstructor
@AllArgsConstructor
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String autor;

    @Column(nullable = false)
    private String dataLeitura;

    @Column(nullable = false)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LivroType status;


    @Override
    public String toString(){
        return "Livro [id="+id+",name="+ name + ",autor="+ autor + ",dataLeitura="+ dataLeitura +
                        ",Descrição="+ descricao+",Status=" + status+"]";
    }

}
