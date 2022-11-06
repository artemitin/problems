
str1 = "1010110"
str2 = "1100100"
result = ""
for i in range(len(str1)):
    c1 = str1[i]
    c2 = str2[i]
    if c1 == "1" and c2 == "1":
        result += "1"
    else:
        result += "0"

print(result)

