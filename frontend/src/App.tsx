import React, {FormEvent, useEffect, useState} from 'react';
import Avatar from '@material-ui/core/Avatar';
import Button from '@material-ui/core/Button';
import CssBaseline from '@material-ui/core/CssBaseline';
import TextField from '@material-ui/core/TextField';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import Checkbox from '@material-ui/core/Checkbox';
import WbIncandescentIcon from '@material-ui/icons/WbIncandescent';
import Typography from '@material-ui/core/Typography';
import {makeStyles} from '@material-ui/core/styles';
import Container from '@material-ui/core/Container';
import axios, {AxiosResponse} from 'axios';
import {MorsePiSettings} from "./types";
import {Endpoint} from "./config";
import {ErrorSnackbar, SuccessSnackbar} from "./snackbar";
import {Spinner} from "./Spinner";

const useStyles = makeStyles((theme) => ({
    paper: {
        marginTop: theme.spacing(8),
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
    },
    avatar: {
        margin: theme.spacing(1),
        backgroundColor: theme.palette.secondary.main,
    },
    form: {
        width: '100%', // Fix IE 11 issue.
        marginTop: theme.spacing(1),
    },
    submit: {
        margin: theme.spacing(3, 0, 2),
    },
}));

const App: React.FC = () => {
    const classes = useStyles();

    const [settings, setSettings] = useState<MorsePiSettings>({
        active: false,
        text: ''
    })
    const [busy, setBusy] = useState<boolean>(true)
    const [successMessageOpen, setSuccessMessageOpen] = useState(false);
    const [errorMessageOpen, setErrorMessageOpen] = useState(false);

    useEffect(() => {
        axios.get(Endpoint.GetBlinkConfig)
            .then((value: AxiosResponse<MorsePiSettings>) => {
                setSettings(value.data)
                setBusy(false)
            }).catch(() => setErrorMessageOpen(true))
    }, [setSettings, setBusy])


    if (busy) {
        return <Spinner/>
    }

    const onSave = (e: FormEvent<HTMLFormElement>) => {
        e.preventDefault()
        setBusy(true);
        axios.get(Endpoint.SetBlinkConfig, {
            params: {
                active: settings.active,
                text: settings.text
            }
        }).then(() => {
            setSuccessMessageOpen(true)
            setBusy(false)
        }).catch(() => setErrorMessageOpen(true))
    }

    return (
        <Container component="main" maxWidth="xs">
            <CssBaseline/>
            <div className={classes.paper}>
                <Avatar className={classes.avatar}>
                    <WbIncandescentIcon/>
                </Avatar>
                <Typography component="h1" variant="h5">
                    MorsePi - Light up your message!
                </Typography>
                <form className={classes.form} noValidate onSubmit={onSave}>
                    <TextField
                        variant="outlined"
                        margin="normal"
                        fullWidth
                        id="text"
                        label="Your message"
                        name="text"
                        autoComplete="text"
                        value={settings.text}
                        onChange={(event => setSettings(prevState => ({
                            ...prevState,
                            text: event.target.value
                        })))}
                        autoFocus
                    />
                    <FormControlLabel
                        control={<Checkbox value="active" color="primary" checked={settings.active} onChange={event => {
                            setSettings(prevState => ({
                                ...prevState,
                                active: !prevState.active
                            }))
                        }}/>}
                        label="Enable blinking"
                    />
                    <Button
                        type="submit"
                        fullWidth
                        variant="contained"
                        color="primary"
                        className={classes.submit}
                    >
                        Save
                    </Button>
                </form>
            </div>
            <SuccessSnackbar open={successMessageOpen} onClose={() => setSuccessMessageOpen(false)}/>
            <ErrorSnackbar open={errorMessageOpen} onClose={() => setErrorMessageOpen(false)}/>
        </Container>
    );
}

export default App
