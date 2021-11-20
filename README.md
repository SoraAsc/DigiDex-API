# DigiDex API

Esse projeto consiste em uma pequena API baseada no jogo Digimon Masters Online.

O projeto ainda está em fase de desenvolvimento e pode vir a ter a sua arquitetura mudada conforme a necessidade.


## Tecnologias Usadas

- Spring Boot
- Lombok
- Thymeleaf
- Mysql
- MapStruct

## Passo a Passo

É necessário inserir as informações relacionados ao seu BD no application.properties.
```
spring.datasource.url=jdbc:mysql://localhost:port/database?useSSL=false&useTimezone=true&serverTimezone=UTC
spring.datasource.username=username
spring.datasource.password=password
```

Para instanciar as dependências necessárias
```
mvn clean install
```

Para executar o projeto no terminal.
```
mvn spring-boot:run
```

## Requisições Disponíveis

- GET
- POST
- PATCH
- DELETE

Endereço Padrão — Responsável pelo List All
```
http://localhost:8080/api/v1/digimon
```

Para buscar, atualizar e deletar por id
```
http://localhost:8080/api/v1/digimon/{id}
```

Parâmetros
```
http://localhost:8080/api/v1/digimon?page=0
http://localhost:8080/api/v1/digimon?orderBy=asc
http://localhost:8080/api/v1/digimon?page=0&orderBy=desc
```


<details>
<summary>Exemplo de Json - Post</summary>

```
{
    "name": "RizeGreymon",
    "imageUrl": "https://static.wikia.nocookie.net/vsbattles/images/d/da/Rize_Greymon_Next_Render.png",
    "hp": 1370,
    "ds": 440,
    "at": 531,
    "ats": 2.4,
    "ct": 0.057,
    "ht": 530,
    "de": 37,
    "ev": 0.21,
    "form": "Ultimate",
    "attribute": "Va",
    "elementalAttribute": "Fire",
    "attackerType": "SA",
    "type": "Cyborg Digimon",
    "families": [
        "DR"
    ],
    "previousEvolutionId": 5,
    "nextEvolutionId": -1,
    "nextEvolutionLv": 41,
    "attacks": [
        {
            "name": "Trident Revolver",
            "imageUrl": "https://dmowiki.com/images/5/56/Trident_Revolver.png",
            "baseDamage": 1396,
            "multiply": 74,
            "elementalAttribute": "Fire",
            "cooldown": 4,
            "dsConsumed": 34
        },{
            "name": "Rising Destroyer",
            "imageUrl": "https://dmowiki.com/images/8/8e/Rising_Destroyer.png",
            "baseDamage": 4001,
            "multiply": 182,
            "elementalAttribute": "Fire",
            "cooldown": 12,
            "dsConsumed": 117
        }
    ]
}
```

</details>

<details>
<summary> Exemplo de Json - Patch </summary>

```
{   
    "ev": 1
}
```

</details>



