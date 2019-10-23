import React from 'react';
import { StyleSheet, Text, View } from 'react-native';
import Calendar from './../components/Calendar';
import TimePicker from './../components/TimePicker';
import SubmitButton from './../components/SubmitButton';
import BackButton from './../components/BackButton';

export default class DateTime extends React.Component {
  constructor(props) {
    super(props)
  }

  render(){
    return(
      <View style={styles.container}>
        <Calendar />
        <TimePicker />
        <SubmitButton continue={this.props.continue}/>
        <BackButton previous={this.props.previous}/>
      </View>
    );
  }
}

let styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
});
