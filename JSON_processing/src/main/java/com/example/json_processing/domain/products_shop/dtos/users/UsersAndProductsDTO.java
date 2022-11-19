package com.example.json_processing.domain.products_shop.dtos.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsersAndProductsDTO {

    private Integer usersCount;

    private List<UserWithProductsDTO> users;

    public UsersAndProductsDTO(List<UserWithProductsDTO> users) {
        this.users = users;
        this.usersCount = users.size();
    }
}
