document.addEventListener('DOMContentLoaded', function () {
    var chat = {
        messageToSend: '',
        messageResponses: [
            'Why did the web developer leave the restaurant? Because of the table layout.',
            'How do you comfort a JavaScript bug? You console it.',
            'An SQL query enters a bar, approaches two tables and asks: "May I join you?"',
            'What is the most used language in programming? Profanity.',
            'What is the object-oriented way to become wealthy? Inheritance.',
            'An SEO expert walks into a bar, bars, pub, tavern, public house, Irish pub, drinks, beer, alcohol'
        ],
        init: function () {
            this.cacheDOM();
            this.bindEvents();
            this.render();
        },
        cacheDOM: function () {
            this.chatHistory = document.querySelector('.chat-history');
            this.button = document.querySelector('button');
            this.textarea = document.getElementById('message-to-send');
            this.chatHistoryList = this.chatHistory.querySelector('ul');
        },
        bindEvents: function () {
            this.button.addEventListener('click', this.addMessage.bind(this));
            this.textarea.addEventListener('keyup', this.addMessageEnter.bind(this));
        },
        render: function () {
            this.scrollToBottom();
            if (this.messageToSend.trim() !== '') {
                var template = Handlebars.compile(document.getElementById('message-template').innerHTML);
                var context = {
                    messageOutput: this.messageToSend,
                    time: this.getCurrentTime()
                };

                this.chatHistoryList.innerHTML += template(context);
                this.scrollToBottom();
                this.textarea.value = '';

                // responses
                var templateResponse = Handlebars.compile(document.getElementById('message-response-template').innerHTML);
                var contextResponse = {
                    response: this.getRandomItem(this.messageResponses),
                    time: this.getCurrentTime()
                };

                setTimeout(function () {
                    this.chatHistoryList.innerHTML += templateResponse(contextResponse);
                    this.scrollToBottom();
                }.bind(this), 1500);
            }
        },

        addMessage: function () {
            this.messageToSend = this.textarea.value;
            this.render();
        },
        addMessageEnter: function (event) {
            // enter was pressed
            if (event.keyCode === 13) {
                this.addMessage();
            }
        },
        scrollToBottom: function () {
            this.chatHistory.scrollTop = this.chatHistory.scrollHeight;
        },
        getCurrentTime: function () {
            return new Date().toLocaleTimeString().replace(/([\d]+:[\d]{2})(:[\d]{2})(.*)/, "$1$3");
        },
        getRandomItem: function (arr) {
            return arr[Math.floor(Math.random() * arr.length)];
        }
    };

    chat.init();

    var searchFilter = {
        options: { valueNames: ['name'] },
        init: function () {
            var userList = new List('people-list', this.options);
            var noItems = document.createElement('li');
            noItems.id = 'no-items-found';
            noItems.innerText = 'No items found';

            userList.on('updated', function (list) {
                if (list.matchingItems.length === 0) {
                    list.list.appendChild(noItems);
                } else {
                    noItems.remove();
                }
            });
        }
    };

    searchFilter.init();
});