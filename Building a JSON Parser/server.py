from flask import Flask, redirect, render_template, request
from json_parser import JSONParser


app = Flask(__name__)
json_parser = JSONParser()

@app.route("/", methods=["GET", "POST"])
def main_page():
    if request.method == "GET":
        return render_template("index.html")
    try:
        dictionary = request.form["dictionary"]
        indent = int(request.form["indent"])
        file_name = request.form["filename"]
        json_parser.object_to_json(data=json.loads(dictionary), json_file_name=f"{file_name}.json", indent=indent)
        return redirect("/")
    except:
        try:
            file_name = request.form["filename"]
            data = json_parser.json_to_object(file_name=f"{file_name}.json")
            return render_template("json.html", data=data)
        except:
            return redirect("/")

if __name__ == "__main__":
    app.run(debug=True)
