# Five Three One
An app for tracking progress with the [5/3/1 strength program](https://www.t-nation.com/workouts/531-how-to-build-pure-strength).

## Prerequisites
- Postgresql server listening on http://localhost:5432 with a database named `531`.
- [Leiningen](http://leiningen.org/)
- Node, for compiling css
- [EditorConfig](http://editorconfig.org)
	1. Install the [core](https://github.com/editorconfig/editorconfig-core-c) by running `brew install editorconfig`
	2. Download a plugin
		- IntelliJ - No plugin necessary
		- [Emacs plugin](https://github.com/editorconfig/editorconfig-emacs#readme)
		- [Sublime Text plugin](https://github.com/sindresorhus/editorconfig-sublime#readme)

## To run:
From the project directory, simply run `./run.sh`

## To deploy:

* Run `docker build -t projectfrank/531 .` from project directory
* Run `docker push projectfrank/531`
* On the server, kill and remove the docker container named 531
	* `docker kill 531 && docker remove 531`
* On the server, run `docker pull projectfrank/531` and then `~/start-app.sh`
