# Forum_Chat
 Application desktop pour un forum de discussion avec RMI 
 
   Le mini-projet proposé consiste à la mise en place d'un forum de discussion permettant
à un nombre quelconque d'intervenants d'échanger des messages en temps réel via une
application utilisateur.

   Les messages ne sont pas mémorisés, et seuls les utilisateurs connectés au forum peuvent
recevoir les messages émis. Le serveur du forum est accessible via l'interface Forum,
tandis que la partie cliente de l'application est gérée par les fichiers User et Proxy. Dans
ce rapport, nous allons explorer les différentes étapes pour la mise en place de ce forum de
discussion en utilisant RMI.

    Le projet est composé de trois parties : le serveur Forum, les clients User et les objets
Proxy. Le serveur Forum fournit les méthodes appelables à distance par les utilisateurs, telles
que l'entrée, la sortie, la diffusion de messages et la récupération de la liste des utilisateurs
connectés. Les clients User sont les parties clientes de l'application et incluent une interface
graphique et les actions déclenchées par les utilisateurs. Les objets Proxy permettent au serveur
Forum de rappeler les clients User pour diffuser un message
 
