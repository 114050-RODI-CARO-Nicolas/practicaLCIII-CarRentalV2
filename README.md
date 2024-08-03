Generar un proyecto en SpringBoot desde 0 usando la herramientas Maven, base de datos H2, Junit como framework para Test Unitarios y que cumpla con los siguientes requisitos.

CRUD de Vehiculo (GET all | GET by id | POST | PUT | DELETE) 
CRUD de Alquiler (GET all | GET by id | POST | PUT | DELETE)Validar que el vehiculo este disponible en la fecha solcitada 
Disponibilidad por fecha (GET by date) Test Completos: Api de disponibilidad (GET by date) 
Alta de alquiler (POST) 



Incluir automatización de Swagger con la generación del archivo swagger.json Como soporte para el desarrollo se adjunta el diagrama DER y de clases.

--H2 insert statements--

INSERT INTO CAR_TYPE (PRICE, TYPE) VALUES (250, 'Hatchback') INSERT INTO CAR_TYPE (PRICE, TYPE) VALUES (300, 'Sedan') INSERT INTO CAR_TYPE (PRICE, TYPE) VALUES (350, 'SUV')

INSERT INTO CAR (BRAND, MODEL, ID_CAR_TYPE) VALUES ('Toyota', 'Yaris', 1) INSERT INTO CAR (BRAND, MODEL, ID_CAR_TYPE) VALUES ('Ford', 'Taurus', 2) INSERT INTO CAR (BRAND, MODEL, ID_CAR_TYPE) VALUES ('Hyundai', 'HRV', 3)

DER:

DER
![der_carrental](https://github.com/user-attachments/assets/81a39acb-094e-4eee-8b85-cf5c4dabdba9)
