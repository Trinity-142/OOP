#!/usr/bin/env python3
import glob
import os
import subprocess
import sys


def run_cmd(cmd):
    """Выполнить команду и проверить результат"""
    print(f"Выполняю: {cmd}")
    result = subprocess.run(cmd, shell=True, capture_output=True, text=True)

    if result.stdout:
        print(f"Вернуло: {result.stdout}")
    if result.stderr:
        print(f"Ошибки: {result.stderr}")


def main():
    """Главная функция"""
    if len(sys.argv) < 2:
        print("""Использование:\n./script.py [MainClass] [args] - полный цикл (compile+jar+run+docs)""")
        return

    java_files = glob.glob("src/main/java/ru/nsu/sharapov/*.java")
    run_cmd(f"javac -d script_files {' '.join(java_files)}")
    os.chdir("script_files")
    run_cmd(f"echo 'Main-Class: ru.nsu.sharapov.{sys.argv[1]}' >> MANIFEST.MF")
    run_cmd(f"jar cfm app.jar MANIFEST.MF ru/nsu/sharapov/*.class")
    run_cmd("rm MANIFEST.MF")
    run_cmd(f"java -jar app.jar {' '.join(sys.argv[2:])}")
    os.chdir("..")
    run_cmd(f"javadoc -d script_files/docs src/main/java/ru/nsu/sharapov/{sys.argv[1]}.java")


if __name__ == "__main__":
    main()
