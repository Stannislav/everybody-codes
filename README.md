# Everybody Codes

https://everybody.codes

## Compiling and Running

Set up classpath and create corresponding directory.

```shell
export CLASSPATH=out
mkdir -p $CLASSPATH
```

Compile all files.

```shell
javac -d "$CLASSPATH" src/**/*.java
```

Run a quest solution.

```shell
java everybody.codes.Quest1
```

Iterate on a quest solution.

```shell
javac -d $CLASSPATH src/everybody/codes/Quest1.java && java everybody.codes.Quest1
```
