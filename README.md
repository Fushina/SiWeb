# TP Noté

Ce TP noté comporte plusieurs étapes, que je vous suggère de réaliser dans l'ordre. 

*Les use cases sont assez incomplets, pour 
vous permettre d'implémenter tout ce qui est demandé. J'ai simplifié le sujet, en supprimant le détail des prises en charge.*

*Vu le temps que vous avez, je ne vous demande pas de gérer les erreurs, en particulier pour les formulaires.*

*Pour gérer l'utilisateur connecté, et en particulier pour savoir si un utilisateur est connecté, je vous suggère d'utiliser le ConnexionService que vous pouvez injecter quand vous en avez besoin.*

Le but de ce programme est de réaliser un gestionnaire de "tickets" qui indiquent des 
problèmes à régler (par exemple un bug).

Les utilisateurs sont les techniciens. Certains sont managers, et ils peuvent assigner un ticket à un autre technicien.

Un ticket est créé par un utilisateur quelconque. Il a un identifiant numérique, une description, une date (normalement, il y avait en plus l'indication que le ticket était fermé, mais nous ne gérerons pas cela aujourd'hui). Le ticket est *optionnellement* assigné à un technicien, qui sera responsable de sa résolution.

Seul un administrateur peut assigner un ticket à un utilisateur.

Le modèle est déjà créé, ainsi qu'un système de connexion.

Trois utilisateurs sont initialisés dans la base : "admin", "tech1" et "tech2".

Votre travail :

- écrivez une implementation (TicketServiceImplementation) de TicketService, 
  en complétant si c'est nécessaire les repositories correspondants.

- en écrivant les contrôleurs et les templates nécessaires, fournissez les pages suivantes:

    - `/ticket/creer` : 
        - en mode GET : affiche le formulaire de création de ticket.
        - en mode POST : crée le ticket correspondant

      il faut qu'un utilisateur soit connecté pour pouvoir créer un ticket. Si aucun utilisateur n'est connecté, on renverra à la page d'accueil.

      On ne gèrera pas les erreurs.

    - `/ticket/liste` : mode GET, réservé aux administrateurs. Liste les tickets (sans leurs détails). Pour chacun d'entre eux, fournit un lien vers la page de gestion (`/ticket/gerer/NUMERO`). 

      **Remarque** : pour créer les liens, vous pouvez suivre le transparent n°61 du [cours Spring MVC](https://deptmedia.cnam.fr/~rosmorse/fip1/progb/cours04/springmvc.pdf). Le code qui suit vous donne une idée de la manière de procéder :

      ~~~html
      <a th:href="@{/ticket/gerer/{id}(id=${t.id})}">gérer</a>
      ~~~

    - `/ticket/gerer/NUMERO` (par exemple /ticket/gerer/1) : 
    formulaire de gestion de TP. permet à un administrateur de gérer un ticket, c'est-à-dire de l'assigner à un utilisateur.
      - GET : affiche le ticket, avec un formulaire qui permettra de saisir dans un champ texte le login du technicien auquel l'administrateur assigne le ticket.
      - POST : enregistre l'assignation.

      Si l'utilisateur n'est pas administrateur, il ne doit pas pouvoir assigner le ticket.

      (si vous avez le temps, mais c'est hors barème, remplacez le champ texte par un select)


**Rappel** : pour placer des arguments dans les URL, on utilise l'annotation @PathVariable.

Par exemple, pour gérer un ticket, la méthode sera quelque chose comme :
~~~java
@GetMapping("/ticket/gerer/{id}")
public String gererTicket(@PathVariable("id") long id, Model model) {
  ...
}
~~~
