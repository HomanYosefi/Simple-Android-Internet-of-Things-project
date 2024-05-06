import socket
from kivy.app import App
from kivy.uix.label import Label
from kivy.clock import Clock

class MyLabel(Label):
    def update_text(self, *args):
        server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        server_socket.bind(('192.168.1.100', 8080))
        server_socket.listen(1)
        client_socket, address = server_socket.accept()
        data = client_socket.recv(1024)
        self.text = data.decode()

class MyApp(App):
    def build(self):
        my_label = MyLabel()
        Clock.schedule_interval(my_label.update_text, 1)
        return my_label

MyApp().run()

