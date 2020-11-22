import React from "react";
import {CircularProgress} from "@material-ui/core";
import {makeStyles} from "@material-ui/core/styles";

const useStyles = makeStyles((theme) => ({
    center: {
        position: 'absolute',
        top: '50%',
        right: '50%',
        transform: 'translate(50%,-50%)',
    },
}));

export const Spinner: React.FC = () => {
    const classes = useStyles();
    return <div className={classes.center}>
        <CircularProgress/>
    </div>
}
