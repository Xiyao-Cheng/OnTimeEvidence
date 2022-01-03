from flask import Flask, request
from flask_restful import Resource, Api
from json import dumps
import json
import scholarly

app = Flask(__name__)
api = Api(app)

#class Scholars_(Resource):
	#def get(self):
		#search_query = scholarly.search_author('Prasad Calyam')
search_query = scholarly.search_author('Prasad Calyam')
author = next(search_query)
author_json = {};
author_json['name']=author.name
author_json['interests']=author.interests
author_json['affiliation']=author.affiliation
#author_json['experties'] = 
                     

#auther_json = json.dumps(author)
print(author)
		#return json.dumps(author)
			
			

		
api.add_resource(Scholars_, '/Scholars_') # Route_1
			
#if __name__ == '__main__':
#	app.run(port='5002')
     
