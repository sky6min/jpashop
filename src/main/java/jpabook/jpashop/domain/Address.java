package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

// jpa 내장타입
@Embeddable
@Getter @Setter
public class Address {

    private String city;
    private String street;
    private String zipcode;
}
