package editProfile;

/**
 * Some helper methods for EditProfileServlet.
 *
 * @author Li Liu
 */
public class EditProfileServerConstants {
    public static final String PAGE_HEADER = """
            <!DOCTYPE html>
            <html xmlns="http://www.w3.org/1999/xhtml">
            <head>
              <title>Edit User Profile</title>
            </head>
            """;

    public static final String STYLE = """
            <style>
              body {
                font-family: Arial, Helvetica, sans-serif;
                background-color: #fae3d9
              }

              * {
                box-sizing: border-box;
              }

              /* Add padding to containers */
              .container {
                padding: 16px;
                background-color: white;
              }

              /* Full-width input fields */
              input[type=text] {
                width: 100%;
                padding: 15px;
                margin: 5px 0 22px 0;
                display: inline-block;
                border: none;
                background: #f1f1f1;
              }

              /* Overwrite default styles of hr */
              hr {
                border: 1px solid #f1f1f1;
                margin-bottom: 25px;
              }

              /* Set a style for the submit button */
              .subbtn {
                background-color: #8ac6d1;
                color: white;
                padding: 16px 20px;
                margin: 8px 0;
                border: none;
                cursor: pointer;
                width: 100%;
                opacity: 0.9;
              }

              .subbtn:hover {
                opacity: 1;
              }

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

            </style>""";


    public static final String NAVI_BODY = """
            <body>
            <ul>
                <li><a href="/home">Home</a></li>
                <li><a class="active" href="/editprofile">Edit Profile</a></li>
                <li><a href="/events">Events</a></li>
                <li><a href="/tickets">Tickets</a></li>
                <li><a href="/logout">Logout</a></li>
            </ul>""";

    public static final String EDIT_BODY = """
            <form action="/editprofile" method="post">
                <div class="container">
                    <h1>Edit Profile</h1>
                    <p>Please fill in this form to change account information.</p>
                    <hr/>

                    <label for="name"><b>Name</b></label>
                    <input type="text" placeholder="Enter Name" name="name" id="name" required="required" />

                    <label><b>Gender</b></label>
                    <input type="radio" name="gender" id="gender1" value="male" required="required" />
                    <label for="gender1">male</label>
                    <input type="radio" name="gender" id="gender2" value="female" required="required" />
                    <label for="gender2">female</label>
            <br></br>
                    <label for="location"><b>Location</b></label>
                    <input type="text" placeholder="Enter City" name="location" id="location" required="required" />
                    <hr/>

                    <button type="submit" class="subbtn">Submit</button>
                </div>

            </form>""";


    public static final String PAGE_FOOTER = """

            </body>
            </html>""";

    public static final String GENDER = "gender";
    public static final String LOCATION = "location";

    public static final String POST_STYLE =
            """
                    <style>
                    .card {
                      box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
                      max-width: 300px;
                      margin: auto;
                      text-align: center;
                      font-family: arial;
                    }

                    .Gender {
                      color: grey;
                      font-size: 18px;
                    }
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
                    </style>""";

    public static final String POST_BODY = """
            <h2 style="text-align:center">User Profile Card</h2>

            <div class="card">
              <img src="https://png.pngitem.com/pimgs/s/130-1300380_female-user-image-icon-hd-png-download.png" alt="John" style="width:100%">""";

    public static final String POST_FOOTER = """
            </div>

            </body>
            </html>""";
}
