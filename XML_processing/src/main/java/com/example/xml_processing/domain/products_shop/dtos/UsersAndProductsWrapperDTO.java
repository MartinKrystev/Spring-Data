package com.example.xml_processing.domain.products_shop.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersAndProductsWrapperDTO {

    @XmlAttribute(name = "count")
    private Integer usersCount;

    @XmlElement(name = "user")
    private List<UserWithProductsDTO> users;

    public UsersAndProductsWrapperDTO(List<UserWithProductsDTO> users) {
        this.users = users;
        this.usersCount = users.size();
    }
}
