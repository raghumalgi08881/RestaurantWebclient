I have developed three apps for the sample task as required.
1.RestaurantWebClient : which loads the webview with local html file from assets folder
2.RestaurantProxyServer : which acts as a httpserver and also triggers the RestaurantDatabase app for getting the
data.Please note this app has no launcher activity,so while installing the app from android studio please edit the
run configuration to "Nothing"
3.RestaurantDataBase:which acts as a datastore and stores the json data provided.Please note this app
has no launcher activity,so while installing the app from android studio please edit the run configuration to "Nothing"


Github url for all three apps.
https://github.com/raghumalgi08881




Handling the requests originating from the Webview are SSL encrypted (https):

Implement a webviewclient and override onReceivedSslError like below which shows a dialog to either
proceed or cancel the request.



@Override
    public void onReceivedSslError(WebView view, final SslErrorHandler handler, SslError error) {
	AlertDialog.Builder builder = new AlertDialog.Builder(ExampleActivity.this);
        AlertDialog alertDialog = builder.create();
        String message = "SSL Certificate error.";
        switch (error.getPrimaryError()) {
            case SslError.SSL_UNTRUSTED:
                message = "The certificate authority is not trusted.";
                break;
            case SslError.SSL_EXPIRED:
                message = "The certificate has expired.";
                break;
            case SslError.SSL_IDMISMATCH:
                message = "The certificate Hostname mismatch.";
                break;
            case SslError.SSL_NOTYETVALID:
                message = "The certificate is not yet valid.";
                break;
        }

        message += " Do you want to continue anyway?";
        alertDialog.setTitle("SSL Certificate Error");
        alertDialog.setMessage(message);
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Ignore SSL certificate errors
                handler.proceed();
            }
        });

        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                handler.cancel();
            }
        });
        alertDialog.show();
    }


}