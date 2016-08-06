#!/bin/bash

echo "Valid Customer"
curl -X POST localhost:8080/checkAccess -d '{"name": "nick"}' -H 'Content-type:application/json'

echo "Invalid Customer"
curl -X POST localhost:8080/checkAccess -d '{"wut": "nick"}' -H 'Content-type:application/json'

echo "Valid Data"
curl -X POST localhost:8080/checkData -d '{"phrase": "STRANGERs","website": "SHT","regions": "asdf","averagePrice": "sg","marginality": "sdf","closeConversation": "asdf","websiteConversation": "asdf","plannedProfit": "asdf"}' -H 'Content-type:application/json'

echo "Valid Data without averagePrice"
curl -X POST localhost:8080/checkData -d '{"phrase": "STRANGERs","website": "SHT","regions": "asdf","marginality": "sdf","closeConversation": "asdf","websiteConversation": "asdf","plannedProfit": "asdf"}' -H 'Content-type:application/json'

echo "Invalid Data"
curl -X POST localhost:8080/checkData -d '{"phrase": "STRANGERs","regions": "asdf","averagePrice": "sg","marginality": "sdf","closeConversation": "asdf","websiteConversation": "asdf","plannedProfit": "asdf"}' -H 'Content-type:application/json'
