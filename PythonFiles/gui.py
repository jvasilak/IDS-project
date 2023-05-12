import json
import tkinter as tk
import tkinter.font as tkFont


def long_lat_command():
    print("longlat selected")

def submit_command():
    value = desc_input.get()
    print(value)

window = tk.Tk()
window.title("TravelTip")
window.title("DestinationFinder")
#setting window size
width=600
height=500
screenwidth = window.winfo_screenwidth()
screenheight = window.winfo_screenheight()
alignstr = '%dx%d+%d+%d' % (width, height, (screenwidth - width) / 2, (screenheight - height) / 2)
window.geometry(alignstr)
window.resizable(width=False, height=False)

title_label=tk.Label(window)
ft = tkFont.Font(family='Times',size=13)
title_label["font"] = ft
title_label["fg"] = "#333333"
title_label["justify"] = "center"
title_label["text"] = "Hello, and welcome to our final project!"
title_label.place(x=140,y=10,width=326,height=30)

subtitle_label=tk.Label(window)
ft = tkFont.Font(family='Times',size=10)
subtitle_label["font"] = ft
subtitle_label["fg"] = "#333333"
subtitle_label["justify"] = "center"
subtitle_label["text"] = "Feel free to test it out!"
subtitle_label.place(x=220,y=30,width=171,height=37)
'''
coord_selector=tk.Checkbutton(window)
coord_selector["anchor"] = "center"
ft = tkFont.Font(family='Times',size=10)
coord_selector["font"] = ft
coord_selector["fg"] = "#333333"
coord_selector["justify"] = "center"
coord_selector["text"] = "Would you like to consider latitude or longitude?"
coord_selector.place(x=160,y=90,width=324,height=30)
coord_selector["offvalue"] = "0"
coord_selector["onvalue"] = "1"
coord_selector["command"] = long_lat_command
'''
quit_button=tk.Button(window)
quit_button["bg"] = "#999999"
ft = tkFont.Font(family='Times',size=10)
quit_button["font"] = ft
quit_button["fg"] = "#fcf9f9"
quit_button["justify"] = "center"
quit_button["text"] = "Quit"
quit_button.place(x=270,y=430,width=87,height=38)
quit_button["command"] = window.destroy
'''
latitude_input=tk.Entry(window)
latitude_input["borderwidth"] = "1px"
ft = tkFont.Font(family='Times',size=10)
latitude_input["font"] = ft
latitude_input["fg"] = "#333333"
latitude_input["justify"] = "center"
latitude_input.place(x=230,y=130,width=80,height=25)

longitude_input=tk.Entry(window)
longitude_input["borderwidth"] = "1px"
ft = tkFont.Font(family='Times',size=10)
longitude_input["font"] = ft
longitude_input["fg"] = "#333333"
longitude_input["justify"] = "center"
longitude_input.place(x=310,y=130,width=80,height=25)
'''
desc_input=tk.Entry(window)
desc_input["borderwidth"] = "1px"
ft = tkFont.Font(family='Times',size=10)
desc_input["font"] = ft
desc_input["fg"] = "#333333"
desc_input["justify"] = "center"
desc_input.place(x=190,y=160,width=248,height=68)

desc_label=tk.Label(window)
ft = tkFont.Font(family='Times',size=10)
desc_label["font"] = ft
desc_label["fg"] = "#333333"
desc_label["justify"] = "center"
desc_label["text"] = "Describe your ideal destination:"
desc_label.place(x=210,y=120,width=206,height=30)

submit_button=tk.Button(window)
submit_button["bg"] = "#51e67b"
ft = tkFont.Font(family='Times',size=10)
submit_button["font"] = ft
submit_button["fg"] = "#000000"
submit_button["justify"] = "center"
submit_button["text"] = "Submit"
submit_button.place(x=280,y=300,width=70,height=25)
submit_button["command"] = submit_command

output_message=tk.Message(window)
ft = tkFont.Font(family='Times',size=10)
output_message["font"] = ft
output_message["fg"] = "#333333"
output_message["justify"] = "center"
output_message["text"] = "Output Here"
output_message.place(x=100,y=340,width=431,height=61)

# Start the GUI event loop
window.mainloop()