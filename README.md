========================
torrentbyemail
========================

    1 - What is it?
    2 - Dependancies
    3 - How to install

========================

1 -What is it?

torrentbyemail was developed to allow me to do remote torrent download from emails.

It performs the following flow:

    - Checks every {n} seconds an Gmail account
    - Verify if there is non-read email
    - If there is new email, then check if is a allowed email
    - Reads the torrent url from Subject and send it to the torrent client for download
    - Mark the email as read

========================

2 - Dependencies

    - JavaMail (https://java.net/projects/javamail/pages/Home)
    
========================

3 - How to install

    git clone https://github.com/andersenmp/torrentbyemail.git
    
    
    Configure torrentbyemail/myConfig.properties

    torrentByMail.pathAppDownload=/Applications/Vuze.app/Contents/MacOS/JavaApplicationStub
    torrentByMail.emailUser=emailaccount@gmail.com
    torrentByMail.emailPassword=emailAccountPassword
    torrentByMail.propStore=imaps
    torrentByMail.server=imap.gmail.com
    torrentByMail.allowedEmails=Email Allowed <emailAllowed@gmail.com>



