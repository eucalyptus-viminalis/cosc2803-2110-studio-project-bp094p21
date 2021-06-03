import pandas as pd

new_headers = []

df = pd.read_csv("original/new_deaths.csv")
headers = df.columns
for header in headers:
    if len(header) == 7 and "/" in header:
        new_headers.append(str("20" + header[-2:] + "-0" + header[0] + "-" + header[2:4]))
    elif len(header) == 8 and "/" in header:
        new_headers.append(str("20" + header[-2:] + "-" + header[0:2] + "-" + header[3:5]))
    else:
        new_headers.append(header)
df.columns = new_headers

df.to_csv("date-formatted/new_deaths_formatted.csv", index=False)

new_headers = []

df = pd.read_csv("original/new_cases.csv")
headers = df.columns
for header in headers:
    if len(header) == 7 and "/" in header:
        new_headers.append(str("20" + header[-2:] + "-0" + header[0] + "-" + header[2:4]))
    elif len(header) == 8 and "/" in header:
        new_headers.append(str("20" + header[-2:] + "-" + header[0:2] + "-" + header[3:5]))
    else:
        new_headers.append(header)
df.columns = new_headers

df.to_csv("date-formatted/new_cases_formatted.csv", index=False)