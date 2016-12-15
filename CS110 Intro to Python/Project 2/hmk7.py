def generateHTML():
    html=''
    html+='<!DOCTYPE html>\n'
    html+='<html>\n'
    html+='<body>\n'

    html+=" <h1>Welcome to USF's Halloween Party!</h1>\n"

    html+='<img src="http://www.how-to-draw-cartoons-online.com/image-files/halloween-cartoons-picture.gif" alt="Halloween" >\n'
    html+='<br>/n'
    html+=' <a href="http://www.usfca.edu/">Visit our University</a>/n'

    html+='<form>\n'
    html+='Enter Your Name:<input type="text" name="name">\n'
    html+='</form>\n'


    html+='<form>\n'
    html+='What costume will you have?<br>\n'
    html+='<input type="radio" name="costume" value="Witch">Witch<br>\n'
    html+='<input type="radio" name="costume" value="Vampire">Vampire<br>\n'
    html+='<input type="radio" name="costume" value="Skeleton">Skeleton\n'
    html+='</form>\n'
    html+='</body>\n'
    html+='</html>\n'
    return html
print(generateHTML())
