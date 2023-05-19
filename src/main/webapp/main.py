import socket
from flask import Flask, redirect

app = Flask(
    __name__,
    template_folder='.',
    static_folder='.',
)


@app.get('/')
def root():
    return redirect('/randompage/randompage.html')


@app.get('/<path:name>')
def other(name: str):
    response = app.send_static_file(name)
    if name.endswith('.js'):
        response.headers['Content-Type'] = 'text/javascript; charset=utf-8'
    return response


if __name__ == '__main__':
    app.run(
        host=socket.gethostbyname(socket.gethostname()),
        port=80,
        debug=True,
    )
