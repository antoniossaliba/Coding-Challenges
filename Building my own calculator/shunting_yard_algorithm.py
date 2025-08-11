def shunting_yard_algorithm():

    precedences = {
        "^": 6,
        "*": 5,
        "/": 5,
        "%": 5,
        "+": 4,
        "-": 4
    }

    output = []
    operator_stack = []

    operation = input("Enter an expression to evaluate: ").split(" ")

    for char in operation:

        if char == ' ':
            continue

        if char not in precedences.keys() and char != '(' and char != ')':
            output.append(char)
        else:
            if char == '(':
                operator_stack.append(char)
            elif char == ')':
                while operator_stack[-1] != '(':
                    output.append(operator_stack.pop())
                operator_stack.pop()
            else:
                while len(operator_stack) > 0 and operator_stack[-1] != '(' and precedences[char] <= precedences[operator_stack[-1]]:
                    output.append(operator_stack.pop())
                operator_stack.append(char)

    while len(operator_stack) > 0:
        output.append(operator_stack.pop())

    return output

def process_operation(operators: list):

    numbers = []

    for i in range(len(operators)):
        if operators[i] != '^' and operators[i] != '*' and operators[i] != '/' and operators[i] != '%' and operators[i] != '+' and operators[i] != '-':
            numbers.append(int(operators[i]))
        else:
            number2 = numbers.pop()
            number1 = numbers.pop()
            if operators[i] == "^":
                numbers.append(number1 ** number2)
            elif operators[i] == "*":
                numbers.append(number1 * number2)
            elif operators[i] == "/":
                numbers.append(number1 / number2)
            elif operators[i] == "%":
                numbers.append(number1 % number2)
            elif operators[i] == "+":
                numbers.append(number1 + number2)
            else:
                numbers.append(number1 - number2)

    return numbers[-1]

print(process_operation(operators=shunting_yard_algorithm()))