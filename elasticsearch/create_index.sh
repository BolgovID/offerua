curl --location --request PUT 'localhost:9200/idx_question' \
--header 'Content-Type: application/json' \
--data '{
    "settings": {
        "analysis": {
            "analyzer": {
                "suggest_analyzer": {
                    "type": "custom",
                    "tokenizer": "edge_ngram_tokenizer",
                    "filter": [
                        "lowercase",
                        "suggest_stop_words_filter"
                    ]
                }
            },
            "tokenizer": {
                "edge_ngram_tokenizer": {
                    "type": "edge_ngram",
                    "min_gram": 1,
                    "max_gram": 20,
                    "token_chars": [
                        "letter",
                        "digit"
                    ]
                }
            },
            "filter": {
                "suggest_stop_words_filter": {
                    "type": "stop",
                    "stopwords": [
                        "&",
                        "AND",
                        "THE",
                        ",",
                        "'\''",
                        "WHY",
                        "WHICH",
                        "WHAT"
                    ]
                }
            }
        }
    },
    "mappings": {
        "properties": {
            "id": {
                "type": "keyword"
            },
            "technology_id": {
                "type": "keyword"
            },
            "technology_name": {
                "type": "text"
            },
            "topic_id": {
                "type": "keyword"
            },
            "topic_name": {
                "type": "text"
            },
            "question_id": {
                "type": "keyword"
            },
            "question": {
                "type": "text"
            },
            "question_suggest": {
                "type": "text",
                "analyzer": "suggest_analyzer"
            }
        }
    }
}'