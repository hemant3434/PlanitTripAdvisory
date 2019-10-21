import React from 'react';
import { StyleSheet, Text, View } from 'react-native';
import Calendar from './../components/Calendar';
import TimePicker from './../components/TimePicker';
import SubmitButton from './../components/SubmitButton';

export default class DateTime extends React.Component {
  render(){
    return(
      <View style={styles.container}>
        <Calendar />
        <TimePicker />
        <SubmitButton />
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
