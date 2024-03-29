def log(func):
    def wrapper(*args, **kw):
        print('call %s()' % func.__name__)
        return func(args, kw)
    return wrapper


@log
def hello(text='world'):
    print('hello, %s' % text)