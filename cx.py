from sys import argv


def arg_or_input(arg_name: str, prompt: str) -> str:
    for item in argv[1:]:
        if item.startswith(arg_name + "="):
            split = item.split("=")
            return input(prompt) if len(split) < 2 else split[1]
    return input(prompt)


def cx(byte: bytes) -> bytes:
    data = b''
    for i, e in enumerate(byte):
        if i % 2 != 0:
            data += bytes([e ^ 0x64])
        else:
            data += bytes([e ^ 0x32])

    return data


f_in = arg_or_input("file", "请输入要操作的文件(带后缀): ")
f_out = arg_or_input("output", "请输入输出的文件名(带后缀): ")

try:
    with open(file=f_in, mode="rb") as f:
        text = f.read()
    print("已读取文件数据")
    with open(file=f_out, mode="wb") as f:
        f.write(cx(text))
    print("已写入数据")

except FileNotFoundError as e:
    print("未找到文件: '" + f_in + "'!")
