# ChooseYourLunch
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/cc4dc3c9b6054b19be5f768cd0f265f3)](https://www.codacy.com/app/valuyskiy/ChooseYourLunch?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=valuyskiy/ChooseYourLunch&amp;utm_campaign=Badge_Grade)
[![Build Status](https://travis-ci.org/valuyskiy/ChooseYourLunch.svg?branch=master)](https://travis-ci.org/valuyskiy/ChooseYourLunch)

## A voting system for deciding where to have lunch.

 * 2 types of users: admin and regular users
 * Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
 * Menu changes each day (admins do the updates)
 * Users can vote on which restaurant they want to have lunch at
 * Only one vote counted per user
 * If user votes again the same day:
    - If it is before 11:00 we asume that he changed his mind.
    - If it is after 11:00 then it is too late, vote can't be changed.
    
 * Each restaurant provides new menu each day.
 
 
# REST API Documentation
 
## Registration
 
**New user registration**
 
 `POST  http://{your.host:port}/rest/register`
 
     curl -X POST \
         http://localhost:8080/rest/register \
         -H 'Content-Type: application/json' \
         -d '{
 	        "email" : "user1@host.com",
 	        "name" : "Jon Smith",
 	        "password" : "P@$$w0rd"
         }'
 
 Response:
 
     Content-Type: application/json;charset=UTF-8
     
     {
         "id": 100055,
         "name": "Jon Smith",
         "email": "user1@host.com",
         "roles": [
             "ROLE_USER"
         ]
     } 
 
     Status codes:
      
       201 - Created
       409 - User with this email already exists
       422 - Fields validation error
   
## Administrator module
 
#### Restaurants
 
**Get all restaurants**
 
 `POST  http://{your.host:port}/rest/admin/restaurants`
 
     curl -X GET \
         http://localhost:8080/rest/admin/restaurants \
         -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWluUGFzc3dvcmQ='
 
 Response:
 
     Content-Type: application/json;charset=UTF-8
     
     [
         {
             "id": 100003,
             "name": "Restaurant 1"
         },
         {
             "id": 100002,
             "name": "Restaurant 2"
         }
     ]
 
     Status codes:
      
       200 - Ok
       401 - Unauthorized
       403 - Forbidden
 
**Get restaurant by ID**
 
`GET  http://{your.host:port}/rest/admin/restaurants/{RestaurantId}`
 
     curl -X POST \
         http://localhost:8080/rest/admin/restaurants \
         -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWluUGFzc3dvcmQ=' \
         -H 'Content-Type: application/json' \
         -d '{
 	        "name" : "New Restaurant"
         }'
 
 Response:
 
     Content-Type: application/json;charset=UTF-8
     
     {
         "id": 100003,
         "name": "Restaurant 1"
     }
 
     Status codes:
      
       200 - Ok
       401 - Unauthorized
       403 - Forbidden
       404 - Restaurant not found
       409 - Restaurant with this name already exists
       422 - Fields validation error
 
**Create new restaurant**
 
 `POST  http://{your.host:port}/rest/admin/restaurants`
 
     curl -X POST \
         http://localhost:8080/rest/admin/restaurants \
         -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWluUGFzc3dvcmQ=' \
         -H 'Content-Type: application/json' \
         -d '{
 	        "name" : "New Restaurant"
         }'
 
 Response:
 
     Content-Type: application/json;charset=UTF-8
     
     {
         "id": 100056,
         "name": "New Restaurant"
     }
 
     Status codes:
      
       201 - Created
       401 - Unauthorized
       403 - Forbidden
       409 - Restaurant with this name already exists
       422 - Fields validation error
 
**Update restaurant**
 
 `PUT  http://{your.host:port}/rest/admin/restaurants/{RestaurantId}`
 
     curl -X PUT \
         http://localhost:8080/rest/admin/restaurants/100003 \
         -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWluUGFzc3dvcmQ=' \
         -H 'Content-Type: application/json' \
         -d '{
             "id": 100003,
             "name": "Lari"
         }'
         
 Response:
 
     Content-Type: application/json;charset=UTF-8
     
     {
         "id": 100003,
         "name": "Lari"
     }
 
     Status codes:
      
       200 - Ok
       401 - Unauthorized
       403 - Forbidden
       404 - Restaurant not found
       409 - Restaurant with this name already exists
       422 - Fields validation error
 
**Delete restaurant**
 
 `DELETE  http://{your.host:port}/rest/admin/restaurants/{RestaurantId}`
 
     curl -X DELETE \
         http://localhost:8080/rest/admin/restaurants/100003 \
         -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWluUGFzc3dvcmQ='
  
 Response:
 
     None        
 
     Status codes:
      
       204 - No content. Deleted
       401 - Unauthorized
       403 - Forbidden
       404 - Restaurant not found
 
#### Menu
 
**Get all menus of the restaurant**
 
 `GET  http://{your.host:port}/rest/admin/restaurants/{RestaurantId}/menus`
 
     curl -X GET \
         http://localhost:8080/rest/admin/restaurants/100002/menus \
         -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWluUGFzc3dvcmQ='
         
 Response:
 
     Content-Type: application/json;charset=UTF-8
     
     [
         {
             "id": 100015,
             "date": "2018-06-06",
             "restaurant_id": 100002
         },
         {
             "id": 100026,
             "date": "2018-06-07",
             "restaurant_id": 100002
         }
     ]
     
     Status codes:
      
       200 - Ok
       401 - Unauthorized
       403 - Forbidden
  
**Get menu by ID**
 
 `GET  http://{your.host:port}/rest/admin/restaurants/{RestaurantId}/menus/{MenuID}`
 
     curl -X GET \
         http://localhost:8080/rest/admin/restaurants/100003/menus/100020 \
         -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWluUGFzc3dvcmQ='
 
 Response:
 
     Content-Type: application/json;charset=UTF-8
         
     {
         "id": 100020,
         "date": "2018-06-06",
         "restaurant_id": 100003
     }
 
     Status codes:
 
       200 - Ok
       401 - Unauthorized
       403 - Forbidden
       404 - Restaurant or menu not found
 
**Create new menu today**
 
 `POST  http://{your.host:port}/rest/admin/restaurants/{RestaurantId}/menus`
 
     curl -X POST \
         http://localhost:8080/rest/admin/restaurants/100003/menus \
         -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWluUGFzc3dvcmQ=' \
         -H 'Content-Type: application/json' 
 
 Response:
 
     Content-Type: application/json;charset=UTF-8
         
     {
         "id": 100051,
         "date": "2018-06-07",
         "restaurant_id": 100003
     }
 
     Status codes:
      
       201 - Created
       401 - Unauthorized
       403 - Forbidden
       404 - Restaurant not found
       409 - Menu for this date already exists
       422 - Fields validation error
  
**Create new menu for date**
  
  `POST  http://{your.host:port}/rest/admin/restaurants/{RestaurantId}/menus/?date={YYYY-MM-DD}`
  
     curl -X POST \
         'http://localhost:8080/rest/admin/restaurants/100003/menus?date=2018-05-25' \
         -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWluUGFzc3dvcmQ=' \
         -H 'Content-Type: application/json' 
  
 Response:
 
     Content-Type: application/json;charset=UTF-8
  
      {
          "id": 100051,
          "date": "2018-05-25",
          "restaurant_id": 100003
      }
   
     Status codes:
     
       201 - Created
       401 - Unauthorized
       403 - Forbidden
       404 - Restaurant not found
       409 - Menu for this date already exists
       422 - Fields validation error
  
**Update menu**
  
  `PUT  http://{your.host:port}/rest/admin/restaurants/{RestaurantId}/menus/{MenuId}`
  
      curl -X PUT \
         http://localhost:8080/rest/admin/restaurants/100003/menus/100009 \
         -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWluUGFzc3dvcmQ=' \
         -H 'Content-Type: application/json' \
         -d '{
             "id": 100009,
             "date": "2018-06-01",
             "restaurant_id": 100002
         }'
   
 Response:
 
     Content-Type: application/json;charset=UTF-8
     {
         "id": 100009,
         "date": "2018-06-01",
         "restaurant_id": 100002
     }
 
     Status codes:
       
       200 - Ok
       401 - Unauthorized
       403 - Forbidden
       404 - Restaurant or menu not found
       409 - Menu for this date already exists
       422 - Fields validation error
  
**Delete menu**
  
  `DELETE  http://{your.host:port}/rest/admin/restaurants/{RestaurantId}/menus/{MenuId}`
   
     curl -X DELETE \
         http://localhost:8080/rest/admin/restaurants/100003/menus/100051 \
         -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWluUGFzc3dvcmQ='
         
 Response:
   
     None
     
     Status codes:
           
     204 - No content
     401 - Unauthorized
     403 - Forbidden
     404 - Restaurant or menu not found
  
  
#### Dishes
 
**Get dishes by menu ID**
  
   `GET  http://{your.host:port}/rest/admin/restaurants/{RestaurantId}/menus/{MenuId}/dishes`
 
      curl -X GET \
        http://localhost:8080/rest/admin/restaurants/100003/menus/100009/dishes \
        -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWluUGFzc3dvcmQ=' 
 
 Response: 
 
      Content-Type: application/json;charset=UTF-8
 
     [
         {
             "id": 100010,
             "name": "Mushroom cream soup",
             "price": 28000,
             "menu_id": 100009
         },
         {
             "id": 100011,
             "name": "Greek salad",
             "price": 35000,
             "menu_id": 100009
         },
         {
             "id": 100012,
             "name": "Mineral water",
             "price": 10000,
             "menu_id": 100009
         }
     ] 
  
     Status codes:
  
       200 - Ok
       401 - Unauthorized
       403 - Forbidden
  
**Get dish by ID**
  
   `GET  http://{your.host:port}/rest/admin/restaurants/{RestaurantId}/menus/{MenuId}/dishes/{DishId}`
 
      curl -X GET \
        http://localhost:8080/rest/admin/restaurants/100003/menus/100009/dishes/100010 \
        -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWluUGFzc3dvcmQ=' 
 
 Response: 
 
      Content-Type: application/json;charset=UTF-8
 
     {
         "id": 100010,
         "name": "Mushroom cream soup",
         "price": 28000,
         "menu_id": 100009
     }
  
     Status codes:
  
       200 - Ok
       401 - Unauthorized
       403 - Forbidden
       404 - Dish not found
   
**Add new dish in menu**
 
   `POST  http://{your.host:port}/rest/admin/restaurants/{RestaurantId}/menus/{MenuId}/dishes`
  
     curl -X POST \
         http://localhost:8080/rest/admin/restaurants/100003/menus/100009/dishes \
         -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWluUGFzc3dvcmQ=' \
         -H 'Content-Type: application/json' \
         -d '    {
              "name": "Mushroom cream soup",
              "price": 28000,
              "menu_id": 100009
         }'
  
 Response: 
 
      Content-Type: application/json;charset=UTF-8
 
     {
         "id": 100051,
         "name": "Mushroom cream soup",
         "price": 28000,
         "menu_id": 100009
     }
  
     Status codes:
  
       201 - Created
       401 - Unauthorized
       403 - Forbidden
       404 - Dish not found 
       409 - Dish with this name already exists
       422 - Fields validation error 
 
**Update dish**
  
   `PUT  http://{your.host:port}/rest/admin/restaurants/{RestaurantId}/menus/{MenuId}/dishes/{DishId}`
 
      curl -X PUT \
         http://localhost:8080/rest/admin/restaurants/100003/menus/100009/dishes/100010 \
             -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWluUGFzc3dvcmQ=' \
             -H 'Content-Type: application/json' \
             -d '{
                 "id": 100010,
                 "name": "Risotto",
                 "price": 48000,
                 "menu_id": 100009
             }'
 
 Response: 
 
      Content-Type: application/json;charset=UTF-8 
  
      {
          "id": 100010,
          "name": "Risotto",
          "price": 48000,
          "menu_id": 100009
      }
  
     Status codes:
  
       200 - Ok
       401 - Unauthorized
       403 - Forbidden
       404 - Dish not found 
       409 - Dish with this name already exists
       422 - Fields validation error   
  
**Delete dish**
  
  `DELETE  http://{your.host:port}/rest/admin/restaurants/{RestaurantId}/menus/{MenuId}/dishes/{DishId}`
  
     curl -X DELETE \
       http://localhost:8080/rest/admin/restaurants/100003/menus/100009/dishes/100012 \
       -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWluUGFzc3dvcmQ=' 
   
  Response:
  
      None        
  
      Status codes:
       
        204 - No content. Deleted
        401 - Unauthorized
        403 - Forbidden
        404 - Restaurant not found
 
#### Users
  
**Get all Users**
    
  `GET http://localhost:8080/rest/admin/users`
  
      curl -X GET \
        http://localhost:8080/rest/admin/users \
        -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWluUGFzc3dvcmQ='
  
 Response: 
 
      Content-Type: application/json;charset=UTF-8  
  
     [
         {
             "id": 100000,
             "name": "Admin",
             "email": "admin@gmail.com",
             "roles": [
                 "ROLE_USER",
                 "ROLE_ADMIN"
             ]
         },
         {
             "id": 100001,
             "name": "User",
             "email": "user@google.ru",
             "roles": [
                 "ROLE_USER"
             ]
         }
     ] 
     
     Status codes:
      
       200 - Ok
       401 - Unauthorized
       403 - Forbidden 
  
**Get User by ID**
 
  `GET http://localhost:8080/rest/admin/users/{UserId}`
  
     curl -X GET \
         http://localhost:8080/rest/admin/users/100000 \
         -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWluUGFzc3dvcmQ='
  
 Response: 
 
      Content-Type: application/json;charset=UTF-8  
      
     {
         "id": 100000,
         "name": "Admin",
         "email": "admin@gmail.com",
         "roles": [
             "ROLE_USER",
             "ROLE_ADMIN"
         ]
     }      
 
     Status codes:
      
       200 - Ok
       401 - Unauthorized
       403 - Forbidden
       404 - User not found 
 
**Create new User** 
 
  `POST  http://{your.host:port}/rest/admin/users`
  
     curl -X POST \
         http://localhost:8080/rest/admin/users \
         -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWluUGFzc3dvcmQ=' \
         -H 'Content-Type: application/json' \
         -d '{
             "name": "Jon Smith",
             "email": "jon@gmail.com",
             "password" : "P@$$w0rd",
             "roles": [
                 "ROLE_USER"
         ]
     }'
  
 Response:
 
     Content-Type: application/json;charset=UTF-8
  
     {
         "id": 100052,
         "name": "Jon Smith",
         "email": "jon@gmail.com",
         "roles": [
             "ROLE_USER"
         ]
     }
   
     Status codes:
     
       201 - Created
       401 - Unauthorized
       403 - Forbidden
       409 - User with this email already exists
       422 - Fields validation error
  
**Update User**
 
  `POST  http://{your.host:port}/rest/admin/users/{UserID}`
  
     curl -X PUT \
         http://localhost:8080/rest/admin/users/100052 \
         -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWluUGFzc3dvcmQ=' \
         -H 'Content-Type: application/json' \
         -d '{
 	        "id": 100052,
             "name": "Jon Brown",
             "email": "jon@gmail.com",
             "password" : "P@$$w0rd",
             "roles": [
                 "ROLE_USER",
                 "ROLE_ADMIN"
             ]
         }'
  
 Response:
 
     Content-Type: application/json;charset=UTF-8
     
     {
         "id": 100052,
         "name": "Jon Brown",
         "email": "jon@gmail.com",
         "roles": [
             "ROLE_USER",
             "ROLE_ADMIN"
         ]
     } 
  
     Status codes:
     
       200 - Ok
       401 - Unauthorized
       403 - Forbidden
       409 - User with this email already exists
       422 - Fields validation error 
  
**Delete User**
 
  `POST  http://{your.host:port}/rest/admin/users/{UserID}`
     
     curl -X DELETE \
         http://localhost:8080/rest/admin/users/100052 \
         -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWluUGFzc3dvcmQ='
  
 Response: 
  
     None
     
      Status codes:
       
        204 - No content. Deleted
        401 - Unauthorized
        403 - Forbidden
        404 - User not found   
 
## User module 
 
#### Profile
 
**Get User profile**
 
  `GET  http://{your.host:port}/rest/user/profile`
 
      curl -X GET \
         http://localhost:8080/rest/user/profile \
         -H 'Authorization: Basic dXNlckBnb29nbGUucnU6dXNlclBhc3N3b3Jk'
 
 Response: 
 
     Content-Type: application/json;charset=UTF-8
 
     {
         "id": 100001,
         "name": "Jon Smith",
         "email": "user@google.ru",
         "roles": [
             "ROLE_USER"
         ]
     }
 
     Status codes:
     
       200 - Ok
       401 - Unauthorized
       403 - Forbidden
       409 - User with this email already exists
       422 - Fields validation error 
  
  
**Update User profile**
 
  `PUT  http://{your.host:port}/rest/user/profile`
 
     curl -X PUT \
         http://localhost:8080/rest/user/profile \
         -H 'Authorization: Basic dXNlckBnb29nbGUucnU6dXNlclBhc3N3b3Jk' \
         -H 'Content-Type: application/json' \
         -d '{
 	        "name":"Jon Smith",
 	        "email":"user@google.ru",
 	        "password":"userPassword"
         }'
 
 Response: 
 
     Content-Type: application/json;charset=UTF-8
 
     {
         "id": 100001,
         "name": "Jon Smith",
         "email": "user@google.ru",
         "roles": [
             "ROLE_USER"
         ]
     }
 
     Status codes:
     
       200 - Ok
       401 - Unauthorized
       403 - Forbidden
       409 - User with this email already exists
       422 - Fields validation error 
 
**Delete User profile**
  
   `DELETE  http://{your.host:port}/rest/user/profile`
  
     curl -X DELETE \
         http://localhost:8080/rest/user/profile \
         -H 'Authorization: Basic dXNlckBnb29nbGUucnU6dXNlclBhc3N3b3Jk' 
         
 Response: 
  
     None
     
      Status codes:
       
        204 - No content. Deleted
        401 - Unauthorized
        
#### Menus for Users
 
**Get today's menus**
 
  `PUT  http://{your.host:port}/rest/user/menus`
 
     curl -X GET \
         http://localhost:8080/rest/user/menus \
         -H 'Authorization: Basic dXNlckBnb29nbGUucnU6dXNlclBhc3N3b3Jk' 
         
 Response: 
 
     Content-Type: application/json;charset=UTF-8
     
     [
         {
             "id": 100026,
             "date": "2018-06-07",
             "restaurant": {
                 "id": 100002,
                 "name": "Oblomov"
             },
             "dishes": [
                 {
                     "id": 100010,
                     "name": "Mushroom cream soup",
                     "price": 28000,
                 },
                 {
                     "id": 100011,
                     "name": "Greek salad",
                     "price": 35000,
                 },
                 {
                     "id": 100012,
                     "name": "Mineral water",
                     "price": 10000,
                 }
             ]
         },
         {
             "id": 100031,
             "date": "2018-06-07",
             "restaurant": {
                 "id": 100003,
                 "name": "The garden"
             },
             "dishes": [
                 {
                     "id": 100032,
                     "name": "BBQ ribs",
                     "price": 66000
                 },
                 {
                     "id": 100033,
                     "name": "Pasta Carbonara",
                     "price": 35000
                 },
                 {
                     "id": 100034,
                     "name": "Juice",
                     "price": 15000
                 },
             ]
         }
     ]
     
      Status codes:
     
       200 - Ok
       401 - Unauthorized
       403 - Forbidden
         
**Get menus for date**
   
 `PUT  http://{your.host:port}/rest/user/menus?date={YYYY-MM-DD}`
 
     curl -X GET \
        'http://localhost:8080/rest/user/menus?date=2018-05-20' \
        -H 'Authorization: Basic dXNlckBnb29nbGUucnU6dXNlclBhc3N3b3Jk' 
         
#### Voting
 
**Voting to menu**
 
 `PUT  http://{your.host:port}/rest/user/menus/{MenuId}/votes`
 
     curl -X PUT \
         http://localhost:8080/rest/user/menus/100031/votes \
         -H 'Authorization: Basic dXNlckBnb29nbGUucnU6dXNlclBhc3N3b3Jk'
   
 Response: 
 
     None
     
      Status codes:
      
        200 - Ok
        401 - Unauthorized
        403 - Forbidden
        422 - Vote is rejected
        
#### Voting statistics 

**Get voting statistics for day**

 `GET  http://{your.host:port}/rest/user/menus/stats?date={YYYY-MM-DD}`
 
     curl -X GET \
       'http://localhost:8080/rest/user/menus/stats?date=2018-05-20' \
       -H 'Authorization: Basic dXNlckBnb29nbGUucnU6dXNlclBhc3N3b3Jk' 

 Response: 
 
     Content-Type: application/json;charset=UTF-8
     
     [
         {
             "votes": 55,
             "menu_id": 100026
         },
         {
             "votes": 32,
             "menu_id": 100031
         }
     ]