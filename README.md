# lutece-demo-site-forms
Lutece Demo site for the FORMS plugin.

## Features

This plugin offers a complete and flexible form management within a Lutece site. User interface : a forms display engine in front-office pages that manage the filling and saving process of the responsesAgent interface : a back-office "multiview" feature to search and display the form responses, and process workflow actions on it. Administrator interface : a back-office feature to design and publish the forms, including steps, transitions, controls, questions and groups of questions.

Plugin-forms is based on the features of the former plugin-form, and adds the followings improvement :

*    forms composed of steps, with powerful transition management between steps,
*    draft-mode : the user can partially fill out a form, save it, and complete later the full form submit,
*    tree-based design of forms with possibility of groups inside groups,
*    multiview feature: a configurable and workflow-capable feature to manage form responses
*    improved management of conditional display of groups/questions,
*    improved validation control management,
*    architecture open to custom entry types

## Workflow

The form responses can be managed in back office through workflows.

The plugin-workflow is also enabled in this image and provide features  like :

* Creation of workflow (conditionnal states and actions)
* processing of workflow actions on responses, 
* response state monitoring, 
* display of action history
* ...


## build the image

`docker build -t site-demo-forms .`

All Lutece images are available at https://hub.docker.com/u/lutece/

## Launch the server


`docker run -p 80:8080 site-demo-forms`

## Connect to the server to see the service

'http://localhost/site-demo-forms'


## Back office management

'http://localhost/site-demo-forms/jsp/admin/AdminMenu.jsp'

Connect to the back office with login/pwd : admin/adminadmin01

## Sources
All Lutece sources are available  at :
'https://github.com/lutece-platform'

more information about Lutèce framework : 'https://fr.lutece.paris.fr/fr/wiki/home.html'
