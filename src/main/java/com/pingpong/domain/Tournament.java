package com.pingpong.domain;

import javax.persistence.*;

@Entity
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TournamentId")
    private Integer Id;
    @Version
    private Integer version;
}