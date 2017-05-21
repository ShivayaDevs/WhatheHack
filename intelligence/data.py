from flask import Flask, request, jsonify
import csv
import knn
import json

app = Flask(__name__)


@app.route("/data",methods = ['POST'])
def hello():
    data = request.json["title"]

    # f = open('shilpi.csv', 'w')
    # f.write(data)
    # f.close()

    # f = open('shilpi.csv','r')
    # data = f.read()
    
    c = csv.writer(open("calldata.csv", "wb"))
    x = data.split(';')
    
    is_present = {}

    for i in range(len(x)) :
        l = []
        actual = x[i].split(',')

        number = actual[0]
        
        if number in is_present :
            if(actual[4] == 0) :
             is_present[number][3]+=duration
            else :
             is_present[number][4] += duration
            time = actual[5]
            slot = 0
            if(time >= 0 and time<5) :
                slot = 0
            elif(time>=5 and time<10) :
                slot = 1
            elif(time>=10 and time<18) :
                slot = 2
            else :
                slot = 3
                is_present[number][5+slot]+=duration

        else :
            duration = actual[3]
            l.append(actual[0])
            l.append(actual[1])
            l.append(actual[2])
            if(actual[4] == 0) :
                l.append(duration)
                l.append(0)
            else :
                l.append(0)
                l.append(duration)
            time = actual[5]
            slot = 0
            if(time >= 0 and time<5) :
                slot = 0
            elif(time>=5 and time<10) :
                slot = 1
            elif(time>=10 and time<18) :
                slot = 2
            else :
                slot = 3

            for j in range(4) :
                if(j == slot) :
                    l.append(duration)
                else :
                    l.append(0)

            is_present[number] = [k for k in l]

    for k,v in is_present.items() :
        c.writerow(v)

    print is_present
    del c


    list_returned = knn.mainfunc("calldata.csv")
    return json.dumps(list_returned)
    return "d"




if __name__ == "__main__":
    app.debug = True
    app.run(host = '0.0.0.0')