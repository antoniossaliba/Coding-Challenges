import json


class JSONParser:

    def __init__(self):
        pass

    def object_to_json(self, data: dict, json_file_name: str, indent: int) -> None:
        with open(json_file_name, "w") as file:
            file.write(json.dumps(data, indent=indent))

    def json_to_object(self, file_name: str) -> dict:
        with open(file_name, "r") as file:
            return json.load(file)

    def stringify_json(self, data: dict) -> str:
        return json.dumps(data)

    def parse_json(self, data: str) -> dict:
        return json.loads(data)