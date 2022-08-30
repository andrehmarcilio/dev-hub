# dev-hub
Projeto desenvolvido durante o 7DaysOfCode - Android - Alura

## Dia 1 - Primeiros passos com o Jetpack Compose 

No primeiro dia do desafio criei minha primeira tela com o Jetpack Compose. 

<img src="https://user-images.githubusercontent.com/103319187/187075387-39ee129c-fd0d-4fe3-96ef-8d3ad47519af.jpg" alt="Screenshot_20220828-090148_DevHub.jpg" width="230"/>


## Dia 2 - Melhorando o Layout

No segundo dia realizamos algumas alterações no Layout para deixá-lo mais agradável. Para desenvolver esta tela utilizei os seguintes elementos de Layout: 'Box', 'Column', 'Image' e o 'Text'.

<img src="https://user-images.githubusercontent.com/103319187/187100276-c20bf1b6-f724-48ca-b103-ab498cf6c1b2.jpg" alt="Screenshot_20220828-090148_DevHub.jpg" width="230"/>


## Dia 3 - Carregando a imagem a partir de uma url com o Coil

No terceiro dia alteramos a forma de carregar a imagem de perfil. Antes ela era carregada por meio de um arquivo local - localizado no Drawable. Agora, ela é carregada a partir de uma url. Para isso, foi utilizada a biblioteca 'coil'.

Link da biblioteca:

https://github.com/coil-kt/coil 

## Dia 4 - Retrofit

Neste dia, adicionamos o Retrofit no projeto para realizar uma comunicação com a API do Github. Para adicionar o Retrofit foram realizados os seguintes passos:

- Configuração Inicial do Retrofit /data/remote
- Criação de uma Interface para realizar a comunicação com a Web Api /data/remote
- Criação de uma interface do repository  (Para ser utilizada nos UseCases) /domain
- Criação do Repository (Que implementa o repository do domain) /data/repository
- Criação dos UseCases /domain 
- Elaboração dos Estados da Main Activity /ui/states
- Criação da View Model da Main Activity (Para gerenciar o estado de maneira eficiente) /ui/viewmodel
- Implementação do Koin para realizar a 'injeção de dependência' /di

![20220830_173430.gif](https://user-images.githubusercontent.com/103319187/187547857-f179ca34-70a4-4ed7-82b5-8b101a0af5bd.gif)