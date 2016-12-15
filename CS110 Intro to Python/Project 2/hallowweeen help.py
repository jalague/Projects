import flask
from flask import request

app = flask.Flask(__name__)

@app.route('/')
def halloween():
    html = ''
    html += '<html>\n'
    html += '<body>\n'
    html+= "<h1>Welcome to USF's Halloween party!</h1>\n"
    html+= '<img src="http://www.townoffrisco.com/wp-content/uploads/2011/09/Halloween.jpg" alt="Halloween image" width="400" height="300"><br>'
    html+= '<a href="http://www.usfca.edu">USF webpage</a>\n'

    html += '<form method="POST" action="">\n'
    html += 'Enter your name <input type="text" name="firstname"/><br>\n'
    html += '</form>\n'

    # Notice how we added a URL /costume to the action 
    html += '<form method="POST" action="/costume">\n'
    
    html+= '<input type="radio" name="costume" value="Witch"checked />Witch<br>\n'
    html+= '<input type="radio" name="costume" value="Vampire" />Vampire<br>\n'
    html+= '<input type="radio" name="costume" value="Skeleton"/>Skeleton<br>\n'
    html += '<input type="submit" value="Enter" />\n'
    html += '</form>\n'
    
    html += '</body>\n'
    html += '</html>\n'
    return html

# Adding a mapping from /costume  URL to the costume function
# Specifying that an HTTP POST request will be processed
@app.route('/costume', methods = ['POST'])
def costume():
     # using the request function to get client's data (the value of the radio buttons)
     selectedCostume = request.form["costume"]
     return 'Your costume is ' + selectedCostume 
     


app.run()
