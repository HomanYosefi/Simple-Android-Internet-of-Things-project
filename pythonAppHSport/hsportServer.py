import socket
import geocoder
import json

def get_car_status():
    return {
        "door": "closed",
        "engine": "off",
        "lights": "off",
        "windows": "up",
        "fuel": "full",
        "water_temp": "normal",
        "tire_pressure": "normal",
        "insurance_expiry": "2024-12-31",
        "location": get_location(),
    }

def get_location():
    g = geocoder.ip('me')
    lat_lng = g.latlng
    return {"latitude": lat_lng[0], "longitude": lat_lng[1]}

def main():
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_socket.bind(('192.168.14.205', 8080))
    server_socket.listen(1)
    
    print("Server is listening on 192.168.14.205:8080")
    
    while True:
        client_socket, address = server_socket.accept()
        print(f"Connection from {address} has been established.")

        data = client_socket.recv(1024).decode()
        print(f"Received data: {data}")

        if data == 'hi':
            response = get_car_status()
            response_json = json.dumps(response)
            print(f"Sending response: {response_json}")
            client_socket.send(response_json.encode())
        elif data == 'lock':
            response = "Door locked"
            print(f"Sending response: {response}")
            client_socket.send(response.encode())
        elif data == 'unlock':
            response = "Door unlocked"
            print(f"Sending response: {response}")
            client_socket.send(response.encode())
        elif data == 'network':
            response = "Network status updated"
            print(f"Sending response: {response}")
            client_socket.send(response.encode())
        elif data == 'trunk':
            response = "Trunk opened"
            print(f"Sending response: {response}")
            client_socket.send(response.encode())
        elif data == 'voice_on':
            response = "Voice alarm turned on"
            print(f"Sending response: {response}")
            client_socket.send(response.encode())
        elif data == 'voice_off':
            response = "Voice alarm turned off"
            print(f"Sending response: {response}")
            client_socket.send(response.encode())
        elif data == 'turn_on':
            response = "Engine turned on"
            print(f"Sending response: {response}")
            client_socket.send(response.encode())
        elif data == 'turn_off':
            response = "Engine turned off"
            print(f"Sending response: {response}")
            client_socket.send(response.encode())
        else:
            response = f"{data}"
            print(f"Sending response: {response}")
            client_socket.send(response.encode())

        client_socket.close()
        print(f"Connection with {address} closed.\n -------------------------------------------------------------------")

if __name__ == "__main__":
    main()
