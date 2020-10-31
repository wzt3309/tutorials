from flask import Flask, render_template, request, abort

app = Flask(__name__)
app.debug = True


with app.test_request_context('/hello', method='GET'):
    # 绑定上下文环境
    assert request.path == '/hello'
    assert request.method == 'GET'


@app.route('/')
@app.route('/<username>')
def hello_world(username=None):
    return render_template('hello.html', name=username)


@app.route('/user/<username>')
def show_user_profile(username):
    return 'User %s' % username


@app.route('/post/<int:post_id>')
def show_post(post_id):
    # post_id = 'str' will not found page
    return "Post %d" % post_id


@app.route("/projects/")
def projects():
    return "projects"


@app.route("/about")
def about():
    return "about"


@app.route("/err/400")
def trigger_400():
    abort(400)


@app.errorhandler(400)
def error_page_400(error):
    return render_template('400.html'), 400


if __name__ == '__main__':
    app.run()
