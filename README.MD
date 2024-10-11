
# Moyses Santos, moyzlevi@gmail.com
# Instruções para rodar o projeto
- Necessário: docker, docker-compose, java 21, postman (opcional)
- Certifique que o java 21 está atrelado ao terminal, não basta ter ele na própria máquina

# Passos 
1. `sudo docker-compose up` na pasta raiz onde este readme está localizado
2. Entre na pasta de cada um dos projetos e rode: `./gradlew clean build bootRun`
3. Abre a `Hotels.postman_collection.json` por import -> select file no postman para ter acesso as requisições dos endpoints

# Ideias que eu gostaria de implementar se tivesse mais tempo

1. Arquitetura com menos acomplamento como clean arch
2. Melhor divisão dos services e testes unitários
3. ExceptionHandler para não utilizar um try-catch no Controller
4. Normalização do banco de dados das colunas comodidades, meio de pagamento e localização
5. enum de status no postgresql e nos microsserviços