FROM loadimpact/k6:latest

COPY scripts/ /k6/

CMD ["run", "/k6/script.js", "--insecure-skip-tls-verify"]