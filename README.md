# twitter-DM
Send and See the twitter direct messages 

In twitter Api's to get conversation for perticular user is not present hence, fetching all the messages and filtering based on the userId and showing the respective messages.

Added code to update the screen when we recieve the message in the MessageScreenViewModel, with function notificationUpdate which checks for the userId, if it is same userId of DM or self than update the screen else shows a snackBar.
