package events;

public class AddEventServletConstants {
    public static final String STYLE = """
            <style>
            ul {
              list-style-type: none;
              margin: 0;
              padding: 0;
              overflow: hidden;
              background-color: #bbded6;
            }

            li {
              float: left;
            }

            li a {
              display: block;
              color: white;
              text-align: center;
              padding: 14px 16px;
              text-decoration: none;
            }

            li a:hover:not(.active) {
              background-color: #bbded6;
            }

            .active {
              background-color: #8ac6d1;
            }

            input[type=text] {
              width: 30%;
              padding: 12px 20px;
              margin: 8px 0;
              box-sizing: border-box;
              border: 2px solid #ccc;
              border-radius: 4px;
            }

            input[type=date] {
              width: 10%;
              padding: 12px 20px;
              margin: 8px 0;
              box-sizing: border-box;
              border: 2px solid #ccc;
              border-radius: 4px;
            }
            input[type=time] {
              width: 10%;
              padding: 12px 20px;
              margin: 8px 0;
              box-sizing: border-box;
              border: 2px solid #ccc;
              border-radius: 4px;
            }

            input[type=number] {
              width: 30%;
              padding: 12px 20px;
              margin: 8px 0;
              box-sizing: border-box;
              border: 2px solid #ccc;
              border-radius: 4px;
            }
            input[type=file] {
              width: 30%;
              padding: 12px 20px;
              margin: 8px 0;
              box-sizing: border-box;
              border: 2px solid #ccc;
              border-radius: 4px;
            }
            textarea {
              width: 30%;
              height: 150px;
              padding: 12px 20px;
              box-sizing: border-box;
              border: 2px solid #ccc;
              border-radius: 4px;
              background-color: #f8f8f8;
              font-size: 16px;
              resize: none;
            }
            label {
                color: #ffb6b9;
                font-weight: bold;
                display: block;
                width: 150px;
                float: left;
            }
            input[type=submit] {
              width: 10%;
              padding: 12px 20px;
              margin: 8px 0;
              box-sizing: border-box;
              border: 2px solid #ccc;
              border-radius: 4px;
              background-color:#bbded6;
            }
            </style>""";

    public static final String ADD_BODY = """
            <form action="/events/add" method="post" enctype="multipart/form-data">
                <label for="title">Title:</label><br/>
                <input type="text" id="title" name="title" required="required" /> <br/><br/>

                <label for="date">Date:</label><br/>
                <input type="date" id="date" name="date" required="required" /> <br/><br/>

                <label for="time">Time:</label><br/>
                <input type="time" id="time" name="time" min="00:00:00" max="24:00:00" required="required" /> <br/><br/>

                <label for="place">Place:</label><br/>
                <input type="text" id="place" name="place" required="required" /> <br/><br/>

                <label for="ticket">Total tickets:</label><br/>
                <input type="number" id="ticket" name="ticket" required="required" /> <br/><br/>

                <label for="description">Description:</label><br/>
                <textarea type="text" id="description" name="description" rows="5" cols="40" required="required"></textarea>
                <br/><br/>

                <label for="file">Upload a image:</label><br/>
                <input type="file" id="file" name="file" required="required" /> <br/>
               \s
                <input type="submit" value="Add"/> <br/>
            </form>""";

    public static final String TITLE = "title";
    public static final String DATE = "date";
    public static final String TIME = "time";
    public static final String PLACE = "place";
    public static final String TICKET = "ticket";
    public static final String DESCRIPTION = "description";
    public static final String FILE = "file";
    public static final String PATH = "/Users/liuli/IdeaProjects/project4-hellosold/images/";

    public static final String ALERT = """
            <!DOCTYPE html>
            <html>
            <head>
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <style>
            .alert {
              padding: 20px;
              background-color: #f44336;
              color: white;
            }

            .closebtn {
              margin-left: 15px;
              color: white;
              font-weight: bold;
              float: right;
              font-size: 22px;
              line-height: 20px;
              cursor: pointer;
              transition: 0.3s;
            }

            .closebtn:hover {
              color: black;
            }
            </style>
            </head>
            <body>

            <h2>Error Messages</h2>

            <p>Click on the "x" symbol to close the alert message.</p>
            <div class="alert">
              <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>\s
              <strong>Ooops!</strong> File not upload successfully.
            </div>

            </body>
            </html>""";
}
